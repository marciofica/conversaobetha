package com.betha.playground.resources;

import com.betha.playground.abstractservice.AbstractService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by marcio.figueiredo on 15/12/2015.
 */
@Controller
@RequestMapping("/competencias")
public class CompetenciasResource extends AbstractService {
    @Override
    public String servico() {
        return "/competencias";
    }

    @Override
    public String caminhoDoArquivo() {
        return "C:/conversao/competencias/competencia.json";
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
