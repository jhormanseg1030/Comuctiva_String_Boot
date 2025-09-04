package com.comuctiva.comuctiva.Mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.comuctiva.comuctiva.Dto.ReembolsosDto;
import com.comuctiva.comuctiva.models.Reembolsos;

@Component
public class ReembolsosMapperImple implements ReembolsosMapper {
@Override
public Reembolsos toReembolsos(ReembolsosDto reembolsosDto){
    if(reembolsosDto==null){
        return null;
    }
    Reembolsos reembolsos = new Reembolsos();
    reembolsos.setId_Rembolso(reembolsosDto.getId_rem());
    reembolsos.setFec_Soli(reembolsosDto.getFe_solici());
    reembolsos.setValor(reembolsosDto.getVal());
    reembolsos.setMotivo(reembolsosDto.getMot());
    reembolsos.setFec_Resp(reembolsosDto.getFe_res());
    reembolsos.setEstado(reembolsosDto.getEsta());
    return reembolsos;
}
@Override
public ReembolsosDto toReembolsosDto( Reembolsos reembolsos){
    if (reembolsos== null) {
        return null;
    }
    ReembolsosDto reembolsosDto = new ReembolsosDto();
    reembolsosDto.setId_rem(reembolsos.getId_Rembolso());
    reembolsosDto.setFe_solici(reembolsos.getFec_Soli());
    reembolsosDto.setVal(reembolsos.getValor());
    reembolsosDto.setMot(reembolsos.getMotivo());
    reembolsosDto.setFe_res(reembolsos.getFec_Resp());
    reembolsosDto.setEsta(reembolsos.getEstado());
    return reembolsosDto;
}
@Override
public List<ReembolsosDto> toReembolsosDtoList(List<Reembolsos>reem){
    if (reem==null) {
        return List.of();
    }
    List<ReembolsosDto> reembolsosDtos=new ArrayList<ReembolsosDto>(reem.size());
    for(Reembolsos reembolsos : reem){
        reembolsosDtos.add(toReembolsosDto(reembolsos));
    }
    return reembolsosDtos;
}
@Override
public void updateReembolsos(Reembolsos reembolsos,ReembolsosDto reembolsosDto){
    if (reembolsosDto==null) {
        return;
    }
    reembolsos.setId_Rembolso(reembolsosDto.getId_rem());
    reembolsos.setFec_Soli(reembolsosDto.getFe_solici());
    reembolsos.setValor(reembolsosDto.getVal());
    reembolsos.setMotivo(reembolsosDto.getMot());
    reembolsos.setFec_Resp(reembolsosDto.getFe_res());
    reembolsos.setEstado(reembolsosDto.getEsta());
}
}
