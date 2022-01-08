package br.com.glabs.equipsys.fornecedor.endpoint;

import br.com.glabs.equipsys.fornecedor.dao.FornecedorDao;
import br.com.glabs.equipsys.fornecedor.dto.FornecedorDTO;
import br.com.glabs.equipsys.fornecedor.mapper.FornecedorMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/fornecedor")
public class FornecedorEndpoint {

    @Autowired
    private FornecedorDao dao;

    @Autowired
    private FornecedorMapper mapper;

    @GetMapping
    public ResponseEntity<FornecedorDTO> get(@RequestParam Long id) {
        return dao.findById(id).map(mapper::toDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

}
