package com.up2promisedland.api.services;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.up2promisedland.api.beans.UsuariosGrupoWrapper;
import com.up2promisedland.api.entities.Grupo;
import com.up2promisedland.api.entities.Nivel;
import com.up2promisedland.api.entities.OrdenPago;
import com.up2promisedland.api.entities.Producto;
import com.up2promisedland.api.entities.Usuario;
import com.up2promisedland.api.entities.UsuariosGrupo;
import com.up2promisedland.api.repositories.GrupoRepository;
import com.up2promisedland.api.repositories.NivelRepository;
import com.up2promisedland.api.repositories.OrdenPagoRepository;
import com.up2promisedland.api.repositories.ProductoRepository;
import com.up2promisedland.api.repositories.UsuarioRepository;
import com.up2promisedland.api.repositories.UsuariosGrupoRepository;
import com.up2promisedland.api.util.enums.Operation;
import com.up2promisedland.api.util.enums.RolEnum;

@Service
public class GrupoServiceImpl implements GrupoService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private GrupoRepository grupoRepository;

	@Autowired
	private UsuariosGrupoRepository usuariosGrupoRepository;

	@Autowired
	private NivelRepository nivelRepository;

	@Autowired
	private ProductoRepository productoRepository;

	@Autowired
	private OrdenPagoRepository ordenPagoRepository;

	@Override
	public List<Grupo> getAll() {
		return grupoRepository.findAll();
	}

	@Override
	public Grupo getGrupoById(Integer id) {
		return grupoRepository.findById(id).get();
	}

	@Override
	public Grupo addGrupo(Grupo grupo) {
		return grupoRepository.save(validateGrupo(Operation.CREATE, grupo));
	}

	@Override
	public Grupo updateGrupo(Integer id, Grupo grupo) {
		grupo.setId(id);
		return grupoRepository.save(validateGrupo(Operation.UPDATE, grupo));
	}

	@Override
	public List<Usuario> getUsuariosWOGrupo() {
		return usuarioRepository.findWithoutGroup();
	}

	@Override
	public List<UsuariosGrupo> getUsuariosByGrupoId(Integer id) {
		Grupo group = grupoRepository.findById(id).get();
		return usuariosGrupoRepository.findByGrupo(group);
	}

	@Override
	public List<UsuariosGrupo> addUsuariosToGroupId(Integer grupo, UsuariosGrupoWrapper usuarios) {

		List<UsuariosGrupo> usersGroup = new ArrayList<UsuariosGrupo>();
		List<OrdenPago> paymentOrders = new ArrayList<OrdenPago>();

		String iniDate;
		String endDate;

		Optional<Grupo> optGroup = grupoRepository.findById(grupo);
		if (!optGroup.isPresent())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "El grupo no existe");

		Producto monthlyProduct = productoRepository.findByProductoAndNivel("Mensualidad", optGroup.get().getNivel());

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		iniDate = formatter.format(optGroup.get().getFechaInicio());
		endDate = formatter.format(optGroup.get().getFechaFin());

		for (Integer user : usuarios.getUsuariosGrupo()) {
			Optional<Usuario> optUser = usuarioRepository.findById(user);
			if (!optUser.isPresent())
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario no encontrado");
			usersGroup.add(new UsuariosGrupo(optGroup.get(), optUser.get(), "Activo"));

			/** build shopping cart **/
			long months = ChronoUnit.MONTHS.between(YearMonth.from(LocalDate.parse(iniDate)),
					YearMonth.from(LocalDate.parse(endDate)));

			//for (int i = 0; i <= months; i++) {
			//	paymentOrders.add(new OrdenPago(optUser.get(), monthlyProduct,
			//			DateUtil.addMonths(optGroup.get().getFechaInicio(), i), "Vigente"));
			//}
		}

		usuariosGrupoRepository.saveAll(usersGroup);
		ordenPagoRepository.saveAll(paymentOrders);

		return usuariosGrupoRepository.findByGrupo(new Grupo(grupo));
	}

	@Override
	public Grupo validateGrupo(Operation operation, Grupo grupo) {

		if (operation == Operation.UPDATE || operation == Operation.DELETE) {
			Optional<Grupo> optGroup = grupoRepository.findById(grupo.getId());
			if (!optGroup.isPresent())
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, "El grupo no existe");
		}

		Optional<Nivel> optNivel = nivelRepository.findById(grupo.getNivel().getId());
		if (!optNivel.isPresent())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "El nivel no existe");

		Optional<Usuario> optProfesor = usuarioRepository.findById(grupo.getProfesor().getId());
		if (!optProfesor.isPresent())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "El usuario no existe");

		if (optProfesor.get().getRol().getId() != RolEnum.PROFESOR.getRol())
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El usuario no es un profesor");

		// grupo.setNombre(grupo.getNombre());
		// grupo.setClave(grupo.getClave());
		grupo.setNivel(optNivel.get());
		grupo.setProfesor(optProfesor.get());
		// grupo.setDias(grupo.getDias());
		// grupo.setHoraInicio(grupo.getHoraInicio());
		// grupo.setHoraFin(grupo.getHoraFin());
		// grupo.setFechaInicio(grupo.getFechaInicio());
		// grupo.setFechaFin(grupo.getFechaFin());
		// grupo.setLink(grupo.getLink());
		grupo.setFechaActualizacion(new Date());

		return grupo;
	}

}
