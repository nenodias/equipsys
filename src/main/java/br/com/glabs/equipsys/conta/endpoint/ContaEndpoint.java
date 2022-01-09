package br.com.glabs.equipsys.conta.endpoint;

import br.com.glabs.equipsys.conta.dao.ContaDao;
import br.com.glabs.equipsys.conta.dto.ContaDTO;
import br.com.glabs.equipsys.conta.mapper.ContaMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/conta")
public class ContaEndpoint {

    @Autowired
    private ContaDao dao;

    @Autowired
    private ContaMapper mapper;

    @GetMapping("/{id}")
    public ResponseEntity<ContaDTO> get(@PathVariable Long id) {
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
