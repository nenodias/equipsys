package br.com.glabs.equipsys.equipamento.endpoint;

import br.com.glabs.equipsys.equipamento.dao.EquipamentoDao;
import br.com.glabs.equipsys.equipamento.dto.EquipamentoDTO;
import br.com.glabs.equipsys.equipamento.entidade.EquipamentoDB;
import br.com.glabs.equipsys.equipamento.mapper.EquipamentoMapper;
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

    @GetMapping
    public ResponseEntity<Page<EquipamentoDTO>> getAll(
            @RequestParam(required = false) String nome,
            @PageableDefault(page = 0, size = 20)
            @SortDefault.SortDefaults({
                    @SortDefault(sort = "id", direction = Sort.Direction.ASC),
                    @SortDefault(sort = "name", direction = Sort.Direction.ASC)
            }) Pageable pageable
    ) {
        final boolean nomeIsPresent = Optional.ofNullable(nome).isPresent();
        Page<EquipamentoDB> response = Page.empty();
        if (nomeIsPresent) {
            response = dao.findAllByNomeContains(nome, pageable);
        } else {
            response = dao.findAll(pageable);
        }
        return ResponseEntity.ok(response.map(mapper::toDTO));
    }

}
