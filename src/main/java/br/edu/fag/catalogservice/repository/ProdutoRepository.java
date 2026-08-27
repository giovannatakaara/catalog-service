package br.edu.fag.catalogservice.repository;

import br.edu.fag.catalogservice.service.domain.ProdutoDomain;
import br.edu.fag.catalogservice.repository.entity.ProdutoEntity;
import br.edu.fag.catalogservice.repository.mapper.ProdutoEntityMapper;
import org.springframework.stereotype.Repository;

@Repository
public class ProdutoRepository implements IProdutoRepository {

private final InterfaceRepositoryJpa interfaceRepositoryJpa;

public ProdutoRepository (InterfaceRepositoryJpa interfaceRepositoryJpa) {
    this.interfaceRepositoryJpa = interfaceRepositoryJpa;
}
 public ProdutoDomain criar (ProdutoEntity produtoEntity) {
    final ProdutoEntity produtoPersistida = interfaceRepositoryJpa.save(produtoEntity);

    return ProdutoEntityMapper.toDomain(produtoPersistida);

 }
}
