package com.magneticmediadatabase.bcs.application.service;

import com.magneticmediadatabase.bcs.application.dto.RemisionFormat;
import com.magneticmediadatabase.bcs.domain.model.*;
import com.magneticmediadatabase.bcs.domain.port.in.CreateIronLlegadaUseCase;
import com.magneticmediadatabase.bcs.domain.port.in.DeleteIronLlegadaUseCase;
import com.magneticmediadatabase.bcs.domain.port.in.RetrieveIronLlegadaUseCase;
import com.magneticmediadatabase.bcs.domain.port.in.UpdateIronLlegadaUseCase;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class IronLlegadaService implements CreateIronLlegadaUseCase, UpdateIronLlegadaUseCase, RetrieveIronLlegadaUseCase, DeleteIronLlegadaUseCase {

    private final CreateIronLlegadaUseCase createIronLlegadaUseCase;
    private final UpdateIronLlegadaUseCase updateIronLlegadaUseCase;
    private final RetrieveIronLlegadaUseCase retrieveIronLlegadaUseCase;
    private final DeleteIronLlegadaUseCase deleteIronLlegadaUseCase;

    RemisionFormat remisionFormat;

    public IronLlegadaService(CreateIronLlegadaUseCase createIronLlegadaUseCase, RetrieveIronLlegadaUseCase retrieveIronLlegadaUseCase,
                       UpdateIronLlegadaUseCase updateIronLlegadaUseCase, DeleteIronLlegadaUseCase deleteIronLlegadaUseCase) {
        this.createIronLlegadaUseCase = createIronLlegadaUseCase;
        this.retrieveIronLlegadaUseCase = retrieveIronLlegadaUseCase;
        this.updateIronLlegadaUseCase = updateIronLlegadaUseCase;
        this.deleteIronLlegadaUseCase = deleteIronLlegadaUseCase;
    }

    @Override
    public IronLlegada createIronLlegada(IronLlegada ironLlegada) {
        ironLlegada.setTipoTransporte(new TipoTransporte(1, "5009"));
        ironLlegada.setUsuario(new Usuario(1, "angie", "vannesa", "hernandez", "chivata"));
        ironLlegada.setUbicacionFisica(new UbicacionFisica(1,"calle 59"));
        ironLlegada.setMedio(new Medio(1,"DZC001L8"));

        //verificar si el numero de remision es numerico y con 10 a 20 digitos
        try {
            int entero = Integer.parseInt(ironLlegada.getNumeroRemision());
            if(ironLlegada.getNumeroRemision().length()>10 != ironLlegada.getNumeroRemision().length()<20){
                remisionFormat=new RemisionFormat(
                        "el campo remision debe tener entre 10 y 20 digitos",
                        HttpStatus.BAD_REQUEST.value());
            }
            else {

                //verificar que el usuario no es null
                var user = ironLlegada.getUsuario().getId();
                if (user == null) {
                    remisionFormat=new RemisionFormat(
                            "el usuario"+ironLlegada.getUsuario().getPrimerApellido()+ "no existe",
                            HttpStatus.BAD_REQUEST.value());
                }
                else{
                //verificar que la ubicacion fisica sea calle 59
                    var ubicar = ironLlegada.getUbicacionFisica().getUbicacion();
                    if (ubicar.equalsIgnoreCase("calle 59")==false) {
                        remisionFormat=new RemisionFormat(
                                "la ubicacion para ingreso debe ser calle 59",
                                HttpStatus.BAD_REQUEST.value());
                        ironLlegada.setUbicacionFisica(new UbicacionFisica(1, "calle 59"));
                    }
                    else {
                        //verificar que el tipo de transporte se encuentre en la lista
                        var transporte = ironLlegada.getTipoTransporte().getNombreTipoTransporte();
                        if (transporte.equalsIgnoreCase("1149")==false || transporte.equalsIgnoreCase("3774")==false) {
                            remisionFormat=new RemisionFormat(
                                    "el tipo de trasnporte debe er el 1149 o el 3774",
                                    HttpStatus.BAD_REQUEST.value());
                        }
                        else {
                            //verificar que el medio exista
                            var media = ironLlegada.getMedio().getId();
                            if (media==null) {
                                remisionFormat=new RemisionFormat(
                                        "por favor ingresas un tipo de transporte valido",
                                        HttpStatus.BAD_REQUEST.value());
                            }
                            else {
                                return createIronLlegadaUseCase.createIronLlegada(ironLlegada);
                            }
                        }
                    }

                }
            }
        } catch (NumberFormatException e) {
                remisionFormat=new RemisionFormat(
                    "el campo remision debe ser numerico",
                    HttpStatus.BAD_REQUEST.value());
        }
        return createIronLlegadaUseCase.createIronLlegada(ironLlegada);
    }

    @Override
    public boolean deleteIronLlegada(Long id) {
        return deleteIronLlegadaUseCase.deleteIronLlegada(id);
    }

    @Override
    public Optional<IronLlegada> getIronLlegada(Long id) {
        return retrieveIronLlegadaUseCase.getIronLlegada(id);
    }

    @Override
    public List<IronLlegada> getAllIronLlegada() {
        return retrieveIronLlegadaUseCase.getAllIronLlegada();
    }

    @Override
    public Optional<IronLlegada> updateIronLlegada(Long id, IronLlegada updateIronLlegada) {
        return updateIronLlegadaUseCase.updateIronLlegada(id,updateIronLlegada);
    }
}
