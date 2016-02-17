package com.betha.playground.resources;

import com.betha.playground.abstractservice.AbstractService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by marcio.figueiredo on 11/02/2016.
 */
@Controller
@RequestMapping("/prestadores")
public class PrestadoresResource extends AbstractService {
    @Override
    public String servico() {
        return "/prestadores";
    }

    @Override
    public String caminhoDoArquivo() {
        return "C:/conversao/competencias/prestadores.json";
    }

    @RequestMapping(method = RequestMethod.GET, path = "/enviar")
    @ResponseBody
    public String enviarCompetencia(){
        return enviar();
    }

    @RequestMapping(method = RequestMethod.GET, path = "/consultar/{hash}")
    @ResponseBody
    public String consultarCompetencia(@PathVariable String hash){
        return consultar(hash);
    }
}
