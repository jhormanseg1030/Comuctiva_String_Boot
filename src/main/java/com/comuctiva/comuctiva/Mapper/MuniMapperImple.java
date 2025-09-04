package com.comuctiva.comuctiva.Mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.comuctiva.comuctiva.Dto.MuniDto;
import com.comuctiva.comuctiva.models.Muni;

@Component
public class MuniMapperImple implements MuniMapper {
@Override
public Muni toMuni(MuniDto muniDto){
    if(muniDto==null){
        return null;
    }
    Muni muni=new Muni();
    muni.setId_muni(muniDto.getId_munic());
    muni.setNom(muniDto.getNombr());
    return muni;
}
@Override
public MuniDto toMuniDto(Muni muni){
    if(muni==null){
        return null;
    }
    MuniDto muniDto=new MuniDto();
    muniDto.setId_munic(muni.getId_muni());
    muniDto.setNombr(muniDto.getNombr());
    return muniDto;
}
@Override
public List<MuniDto> toMuniDtoList(List<Muni>munis){
    if(munis==null){
        return null;
    }
    List<MuniDto>muniDtos=new ArrayList<MuniDto>(munis.size());
    for(Muni muni : munis){
        muniDtos.add(toMuniDto(muni));
    }
    return muniDtos;
}
@Override
public void updateMuni(Muni muni,MuniDto muniDto){
    if(muniDto == null){
        return;
    }
    muni.setId_muni(muniDto.getId_munic());
    muni.setNom(muniDto.getNombr());
}
}
