package br.com.glabs.equipsys.obra.endpoint;

import br.com.glabs.equipsys.obra.dao.ObraDao;
import br.com.glabs.equipsys.obra.dto.ObraDTO;
import br.com.glabs.equipsys.obra.mapper.ObraMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/obra")
public class ObraEndpoint {

    @Autowired
    private ObraDao dao;

    @Autowired
    private ObraMapper mappper;

    @GetMapping
    public ResponseEntity<ObraDTO> get(@RequestParam Long id) {
        return dao.findById(id).map(mappper::toDTO)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

}
