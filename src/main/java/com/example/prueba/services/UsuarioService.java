package com.example.prueba.services;

import com.example.prueba.models.UsuarioModel;
import com.example.prueba.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;

@Service
public class UsuarioService {
    @Autowired
    UsuarioRepository usuarioRepository;

    public ArrayList<UsuarioModel> obtenerUsuarios(){
        return  (ArrayList<UsuarioModel>)usuarioRepository.findAll();
    }

    public UsuarioModel guardarUsuario(UsuarioModel usuario){
        return usuarioRepository.save(usuario);
    }

    public Optional<UsuarioModel> obtenerPorId(Long id){
        return usuarioRepository.findById(id);
    }

    public ArrayList<UsuarioModel> obtenerPorPrioridad(Integer prioridad){
        return usuarioRepository.findByPrioridad(prioridad);
    }

    public boolean eliminarUsuario(Long id){
        try{
            usuarioRepository.deleteById(id);
            return true;
        }catch (Exception err){
            return false;
        }

    }

    public UsuarioModel editarUsuario(UsuarioModel usuarioModel ,Long id) {

        return usuarioRepository.findById(id)
        .map(
            UsuarioModel -> {
                UsuarioModel.setName(usuarioModel.getName());
                UsuarioModel.setEmail(usuarioModel.getEmail());
                UsuarioModel.setPrioridad(usuarioModel.getPrioridad());
                return usuarioRepository.save(UsuarioModel);
            }
        ).get();
    }
}
