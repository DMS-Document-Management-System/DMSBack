package com.si.apirest.factory;

import org.modelmapper.ModelMapper;

import com.si.apirest.dto.Bitacora.BitacoraDTO;
import com.si.apirest.entity.Bitacora;
import com.si.apirest.security.util.AESUtil;

public class BitacoraFactory {
    
    public static Bitacora createBitacora(BitacoraDTO bitacoraDTO) {
        ModelMapper modelMapper = new ModelMapper();
        encryptBitacora(bitacoraDTO);
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
            if (bitacoraDTO.getAccion() != null)
                bitacoraDTO.setAccion(AESUtil.encriptar(bitacoraDTO.getAccion()));
            if (bitacoraDTO.getIp() != null)
                bitacoraDTO.setIp(AESUtil.encriptar(bitacoraDTO.getIp()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void encryptBitacora(Bitacora bitacora) {
        try {
            if (bitacora.getAccion() != null)
                bitacora.setAccion(AESUtil.encriptar(bitacora.getAccion()));
            if (bitacora.getIp() != null)
                bitacora.setIp(AESUtil.encriptar(bitacora.getIp()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void decryptBitacora(BitacoraDTO bitacoraDTO) {
        try {
            if (bitacoraDTO.getAccion() != null)
                bitacoraDTO.setAccion(AESUtil.desencriptar(bitacoraDTO.getAccion()));
            if (bitacoraDTO.getIp() != null)
                bitacoraDTO.setIp(AESUtil.desencriptar(bitacoraDTO.getIp()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
