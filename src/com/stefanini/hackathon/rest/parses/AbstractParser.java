package com.stefanini.hackathon.rest.parses;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractParser<E, DTO>{

	abstract E toEntity(DTO dto);
	abstract DTO toDTO(E e);
	
	public List<E> toListEntity(List<DTO> listDTO) {
		List<E> listEntity = new ArrayList<>();
		
		for (DTO dto : listDTO) {
			listEntity.add(toEntity(dto));
		}
		
		return listEntity;
	}
	
	public List<DTO> toListDTO(List<E> listEntity) {
		List<DTO> listDTO = new ArrayList<>();
		listEntity.forEach(e -> listDTO.add(toDTO(e)));
		return listDTO;
	}
	
}
