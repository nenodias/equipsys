package br.com.glabs.equipsys.conta.endpoint;

import br.com.glabs.equipsys.conta.dao.ContaDao;
import br.com.glabs.equipsys.conta.dto.ContaDTO;
import br.com.glabs.equipsys.conta.entidade.ContaDB;
import br.com.glabs.equipsys.conta.mapper.ContaMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
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

    @GetMapping
    public ResponseEntity<Page<ContaDTO>> getAll(
            @RequestParam(required = false) String nome,
            @PageableDefault(page = 0, size = 20)
            @SortDefault.SortDefaults({
                    @SortDefault(sort = "id", direction = Sort.Direction.ASC),
                    @SortDefault(sort = "name", direction = Sort.Direction.ASC)
            }) Pageable pageable
    ) {
        final boolean nomeIsPresent = Optional.ofNullable(nome).isPresent();
        Page<ContaDB> response = Page.empty();
        if (nomeIsPresent) {
            response = dao.findAllByNomeContains(nome, pageable);
        } else {
            response = dao.findAll(pageable);
        }
        return ResponseEntity.ok(response.map(mapper::toDTO));
    }

}
