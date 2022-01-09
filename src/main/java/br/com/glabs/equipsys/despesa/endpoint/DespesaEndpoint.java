package br.com.glabs.equipsys.despesa.endpoint;

import br.com.glabs.equipsys.despesa.dao.DespesaDao;
import br.com.glabs.equipsys.despesa.dto.DespesaDTO;
import br.com.glabs.equipsys.despesa.mapper.DespesaMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/despesa")
public class DespesaEndpoint {

    @Autowired
    private DespesaDao dao;

    @Autowired
    private DespesaMapper mapper;

    @GetMapping("/{id}")
    public ResponseEntity<DespesaDTO> get(@PathVariable Long id) {
        return dao.findById(id).map(mapper::toDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


}
