/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.credibanco.util;

import com.peregrine.servicecenter.PWS.AppmovilInstanceType;
import com.peregrine.servicecenter.PWS.AppmovilInstanceTypeDescripcion;
import com.peregrine.servicecenter.PWS.AppmovilKeysType;
import com.peregrine.servicecenter.PWS.AppmovilModelType;
import com.peregrine.servicecenter.PWS.Common.MessageType;
import com.peregrine.servicecenter.PWS.Common.StringType;
import com.peregrine.servicecenter.PWS.Crcappmovil_BindingStub;
import com.peregrine.servicecenter.PWS.Crcappmovil_ServiceLocator;
import com.peregrine.servicecenter.PWS.CreateappmovilRequest;
import com.peregrine.servicecenter.PWS.CreateappmovilResponse;
/**
 *
 * @author 
 */
public class CanalAppWsC {

    /**
     * @param args the command line arguments
     * @throws java.lang.Exception
     */
    public static void main(String[] args) throws Exception {
        Crcappmovil_BindingStub binding;
        try {
            binding = (Crcappmovil_BindingStub) new Crcappmovil_ServiceLocator().getcrcappmovil();
        } catch (javax.xml.rpc.ServiceException jre) {
            if (jre.getLinkedCause() != null) {
                jre.getLinkedCause().printStackTrace();
            }
            throw jre;
        }

        binding.setTimeout(60000);
        CreateappmovilResponse value = null;

        AppmovilModelType appmovilModelType = new AppmovilModelType();
        AppmovilInstanceType appmovilInstanceType = new AppmovilInstanceType();

        appmovilInstanceType.setClasificacion(new StringType("Hardware"));
        appmovilInstanceType.setNombre(new StringType("YONI FERNANDO HERNANDEZ TERAN"));
        appmovilInstanceType.setTipocliente(new StringType("Comercio"));
        appmovilInstanceType.setProducto(new StringType("Datáfonos"));
        appmovilInstanceType.setTipificacion(new StringType("Impresora no arroja voucher"));
        appmovilInstanceType.setCodigounico(new StringType("15551286"));
        appmovilInstanceType.setTiposolicitud(new StringType("Queja"));
        appmovilInstanceType.setDireccion(new StringType("Cra 5#16-74"));
        appmovilInstanceType.setCorreo(new StringType("construceramicasatenas@yahoo.com"));
        appmovilInstanceType.setTelefono(new StringType("7657862"));
        appmovilInstanceType.setTipotec(new StringType("01"));
        AppmovilInstanceTypeDescripcion description = new AppmovilInstanceTypeDescripcion();
        StringType[] descriptions = new StringType[1];
        descriptions[0] = new StringType("Falla");
        description.setDescripcion(descriptions);
        appmovilInstanceType.setDescripcion(description);

        appmovilModelType.setInstance(appmovilInstanceType);

        CreateappmovilRequest request = new CreateappmovilRequest();
        request.setModel(appmovilModelType);

        AppmovilKeysType appmovilKeysType = new AppmovilKeysType();
        appmovilKeysType.setQuery("");
        appmovilModelType.setKeys(appmovilKeysType);

        binding.setUsername("integracionapp");
        binding.setPassword("C0L0mB141");

        value = binding.createappmovil(request);

        System.out.println(value.getMessage());
        for (MessageType msg : value.getMessages()) {
            System.out.println(msg.get_value());
        }
    }

}
