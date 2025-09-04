package com.comuctiva.comuctiva.Mapper;

import java.util.List;

import com.comuctiva.comuctiva.Dto.MuniDto;
import com.comuctiva.comuctiva.models.Muni;

public interface MuniMapper {
Muni toMuni(MuniDto muniDto);
MuniDto toMuniDto(Muni muni);
List<MuniDto> toMuniDtoList(List<Muni>munis);
void updateMuni(Muni muni, MuniDto muniDto);
}
