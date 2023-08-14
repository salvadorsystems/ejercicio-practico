package api.rest.Devsu.ejerciciopractico.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import api.rest.Devsu.ejerciciopractico.dto.MovimientoDTO;
import api.rest.Devsu.ejerciciopractico.model.CuentaModel;
import api.rest.Devsu.ejerciciopractico.model.MovimientoModel;
import api.rest.Devsu.ejerciciopractico.repository.CuentaRepository;
import api.rest.Devsu.ejerciciopractico.repository.MovimientoRepository;
import api.rest.Devsu.ejerciciopractico.service.MovimientoService;
import api.rest.Devsu.ejerciciopractico.service.exception.ServiceException;

@Service
public class MovimientoServiceImpl implements MovimientoService {

	double contadorLimite = 0;
	List<String> cuentas = new ArrayList<>();

	@Autowired
	private MovimientoRepository movimientoRepository;
	@Autowired
	private CuentaServiceImpl cuentaServiceImpl;

	@Autowired
	private CuentaRepository cuentaRepository;

	@Override
	public List<MovimientoDTO> findMovimientoAll() {
		// TODO Auto-generated method stub
		List<MovimientoModel> movimientosModel = movimientoRepository.findAll();

		List<MovimientoDTO> movimientosDTO = mapToMovimientoDTO(movimientosModel);

		return movimientosDTO;
	}

	@Override
	public MovimientoModel createMovimiento(MovimientoDTO movimiento) {
		// TODO Auto-generated method stub

		MovimientoModel movimientoModel = mapToMovimientoModel(movimiento);
		CuentaModel cuentaModel = movimientoModel.getCuentaModel();
		double valorLimite = 1000;

		cuentas.add(cuentaModel.getNumCuenta());

		if (!movimientoModel.getTipMov().equalsIgnoreCase("Deposito")) {
			contadorLimite += movimientoModel.getValor();
			for (String string : cuentas) {
				if (!string.equalsIgnoreCase(cuentaModel.getNumCuenta())) {
					contadorLimite = 0;
					cuentas = new ArrayList<>();
				}
			}
		}

		if (!movimientoModel.getTipMov().equalsIgnoreCase("Deposito") && contadorLimite > valorLimite) {
			throw new ServiceException("Cupo Diario Exedido.");
		}

		if (!movimientoModel.getTipMov().equalsIgnoreCase("Deposito")
				&& cuentaModel.getSaldoInit() < movimientoModel.getValor()) {
			throw new ServiceException("Saldo no Disponible.");
		}

		movimientoModel.setSaldoDisponible(cuentaModel.getSaldoInit());
		cuentaModel.setSaldoInit(movimientoModel.getTipMov().equalsIgnoreCase("Deposito")
				? movimientoModel.getSaldoDisponible() + movimientoModel.getValor()
				: movimientoModel.getSaldoDisponible() - movimientoModel.getValor());
		cuentaRepository.save(cuentaModel);

		return movimientoRepository.saveAndFlush(movimientoModel);
	}

	@Override
	public MovimientoDTO updateMovimiento(MovimientoDTO movimiento) {
		// TODO Auto-generated method stub

		MovimientoModel movimientoModel = mapToMovimientoModel(movimiento);
		movimientoRepository.save(movimientoModel);
		// aca se actualiza la misma clase por el de BD
		movimiento = getMovimiento(movimientoModel.getId());
		return movimiento;
	}

	@Override
	public void deleteMovimiento(Long id) {
		// TODO Auto-generated method stub
		movimientoRepository.deleteById(id);
	}

	@Override
	public List<MovimientoDTO> getMovimientoByNumDoc(String numDoc) {
		// TODO Auto-generated method stub
		List<MovimientoModel> movimientosModel = movimientoRepository.findByCuentaModel_ClienteModel_NumDoc(numDoc);
		List<MovimientoDTO> movimientosDTO = mapToMovimientoDTO(movimientosModel);
		return movimientosDTO;
	}

	private List<MovimientoDTO> mapToMovimientoDTO(List<MovimientoModel> movimientosModel) {
		// TODO Auto-generated method stub
		List<MovimientoDTO> movimientosDTO = new ArrayList<>();
		for (MovimientoModel movimientoModel : movimientosModel) {
			MovimientoDTO movimientoDTO = new MovimientoDTO();
			movimientoDTO.setId(movimientoModel.getId());
			movimientoDTO.setFecha(movimientoModel.getFecha());
			movimientoDTO.setTipMov(movimientoModel.getTipMov());
			movimientoDTO.setValor(movimientoModel.getValor());
			movimientoDTO.setSaldoDisponible(movimientoModel.getSaldoDisponible());
			movimientoModel.getCuentaModel()
					.setSaldoInit(movimientoModel.getTipMov().equalsIgnoreCase("Deposito")
							? movimientoModel.getCuentaModel().getSaldoInit() - movimientoModel.getValor()
							: movimientoModel.getCuentaModel().getSaldoInit() + movimientoModel.getValor());
			if (movimientoModel.getCuentaModel() != null) {
				List<CuentaModel> cuentas = new ArrayList<>();
				cuentas.add(movimientoModel.getCuentaModel());

				movimientoDTO.setCuentaDTO(cuentaServiceImpl.mapToCuentaDTO(cuentas).get(0));

			}
			movimientosDTO.add(movimientoDTO);
		}

		return movimientosDTO;
	}

	private MovimientoModel mapToMovimientoModel(MovimientoDTO movimiento) {
		// TODO Auto-generated method stub
		MovimientoModel movimientoModel = new MovimientoModel();
		movimientoModel.setId(movimiento.getId());
		movimientoModel.setFecha(movimiento.getFecha());
		movimientoModel.setTipMov(movimiento.getTipMov());
		movimientoModel.setValor(movimiento.getValor());
		movimientoModel.setSaldoDisponible(movimiento.getSaldoDisponible());

		if (movimiento.getCuentaDTO() != null) {

			List<CuentaModel> cuentasModel = cuentaRepository.findByNumCuenta(movimiento.getCuentaDTO().getNumCuenta());

			movimientoModel.setCuentaModel(cuentasModel.get(0));
		}

		return movimientoModel;
	}

	@Override
	public MovimientoDTO getMovimiento(Long id) {
		// TODO Auto-generated method stub
		List<MovimientoModel> movimientosModel = new ArrayList<>();
		Optional<MovimientoModel> MovimientoModel = movimientoRepository.findById(id);
		movimientosModel.add(MovimientoModel.get());
		List<MovimientoDTO> movimiento = mapToMovimientoDTO(movimientosModel);

		return movimiento.get(0);
	}
}
