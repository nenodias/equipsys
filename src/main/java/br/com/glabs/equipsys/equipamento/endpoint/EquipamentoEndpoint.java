package br.com.glabs.equipsys.equipamento.endpoint;

import br.com.glabs.equipsys.equipamento.dao.EquipamentoDao;
import br.com.glabs.equipsys.equipamento.dto.EquipamentoDTO;
import br.com.glabs.equipsys.equipamento.mapper.EquipamentoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/equipamento")
public class EquipamentoEndpoint {

    @Autowired
    private EquipamentoDao dao;

    @Autowired
    private EquipamentoMapper mapper;

    @GetMapping("/{id}")
    public ResponseEntity<EquipamentoDTO> get(@PathVariable Long id) {
        return dao.findById(id).map(mapper::toDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/count")
    public ResponseEntity<Long> countByFilter(@RequestParam(defaultValue = "") String nome) {
        return Optional.ofNullable(dao.countByNome(nome))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

}
