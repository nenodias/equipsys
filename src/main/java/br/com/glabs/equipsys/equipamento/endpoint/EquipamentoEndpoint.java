package br.com.glabs.equipsys.equipamento.endpoint;

import br.com.glabs.equipsys.equipamento.dao.EquipamentoDao;
import br.com.glabs.equipsys.equipamento.dto.EquipamentoDTO;
import br.com.glabs.equipsys.equipamento.mapper.EquipamentoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/equipamento")
public class EquipamentoEndpoint {

    @Autowired
    private EquipamentoDao dao;

    @Autowired
    private EquipamentoMapper mapper;

    @GetMapping
    public ResponseEntity<EquipamentoDTO> get(@RequestParam Long id) {
        return dao.findById(id).map(mapper::toDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

}
