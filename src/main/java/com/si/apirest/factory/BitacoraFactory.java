package com.si.apirest.factory;

import org.modelmapper.ModelMapper;

import com.si.apirest.dto.Bitacora.BitacoraDTO;
import com.si.apirest.entity.Bitacora;
import com.si.apirest.security.util.AESUtil;

public class BitacoraFactory {
    
    public static Bitacora createBitacora(BitacoraDTO bitacoraDTO) {
        ModelMapper modelMapper = new ModelMapper();
        encryptBitacora(bitacoraDTO);
        System.out.println(bitacoraDTO);
        return modelMapper.map(bitacoraDTO, Bitacora.class);
    }

    public static BitacoraDTO createBitacoraDTO(Bitacora bitacora) {
        ModelMapper modelMapper = new ModelMapper();
        BitacoraDTO bitacoraDTO = modelMapper.map(bitacora, BitacoraDTO.class);
        decryptBitacora(bitacoraDTO);
        return bitacoraDTO;
    }

    public static void encryptBitacora(BitacoraDTO bitacoraDTO) {
        try {
            bitacoraDTO.setAccion(AESUtil.encriptar(bitacoraDTO.getAccion()));
            bitacoraDTO.setIp(AESUtil.encriptar(bitacoraDTO.getIp()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void decryptBitacora(BitacoraDTO bitacoraDTO) {
        try {
            bitacoraDTO.setAccion(AESUtil.desencriptar(bitacoraDTO.getAccion()));
            bitacoraDTO.setIp(AESUtil.desencriptar(bitacoraDTO.getIp()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
