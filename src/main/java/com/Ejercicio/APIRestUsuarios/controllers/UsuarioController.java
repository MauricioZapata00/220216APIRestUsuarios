package com.Ejercicio.APIRestUsuarios.controllers;

import com.Ejercicio.APIRestUsuarios.models.UsuarioModel;
import com.Ejercicio.APIRestUsuarios.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
    @Autowired
    UsuarioService usuarioService;

    @GetMapping()
    public ArrayList<UsuarioModel> obtenerUsuarios(){
        return usuarioService.obtenerUsuarios();
    }

    @PostMapping()
    public UsuarioModel guardarUsuario(@RequestBody UsuarioModel usuario){
        return this.usuarioService.guardarUsuario(usuario);
    }

    @GetMapping(path = "/{id}")
    public Optional<UsuarioModel> obtenerUsuarioPorId(@PathVariable("id") Long id){
        return this.usuarioService.obtenerPorId(id);
    }

    @GetMapping(path = "/query")
    public ArrayList<UsuarioModel> obtenerUsuarioPorPrioridad(@RequestParam("prioridad") Integer prioridad){
        return this.usuarioService.obtenerPorPrioridad(prioridad);
    }

    @DeleteMapping(path = "/{id}")
    public String eliminarPorId(@PathVariable("id") Long id){
        boolean ok =  this.usuarioService.eliminarUsuario(id);
        if (ok){
            return "Se eliminó el usuario con id: " + id.toString();
        }
        return "No se pudo eliminar el usuario con id: " + id.toString();
    }

    @PostMapping(path = "/actualizar")
    public UsuarioModel actualizarUsuario(@RequestBody UsuarioModel usuario){
        if (usuario.toString().contains("id")){
            //Se hace un update como ya estaba hecho antes solo que con otro endpoint
            return this.usuarioService.actualizarUsuario(usuario);
        }
        //Si no se pasa el id correspondiente, se crea uno nuevo
        return this.guardarUsuario(usuario);
    }

    @DeleteMapping(path = "/borrarTodo")
    public String eliminarTodo(){
        this.usuarioService.eliminarTodo();
        return "Se borraron todos los datos de la tabla. \t \t D:";
    }
}
