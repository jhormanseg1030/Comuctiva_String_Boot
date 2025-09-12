package com.comuctiva.comuctiva.services;

import java.util.List;

import com.comuctiva.comuctiva.Dto.MuniDto;

public interface MuniServices {

    MuniDto crearMuniDto(MuniDto muniDto);

    MuniDto muniPorId(Integer id);

    List<MuniDto> listartodos();

    void updateMuni(Integer id, MuniDto muniDto);
}
