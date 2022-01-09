package br.com.glabs.equipsys.obra.endpoint;

import br.com.glabs.equipsys.obra.dao.ObraDao;
import br.com.glabs.equipsys.obra.dto.ObraDTO;
import br.com.glabs.equipsys.obra.mapper.ObraMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/obra")
public class ObraEndpoint {

    @Autowired
    private ObraDao dao;

    @Autowired
    private ObraMapper mappper;

    @GetMapping("/{id}")
    public ResponseEntity<ObraDTO> get(@PathVariable Long id) {
        return dao.findById(id).map(mappper::toDTO)
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
