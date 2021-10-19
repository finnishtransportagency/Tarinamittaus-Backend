import React from 'react';

import { CustomNumber } from "../components/CustomNumber";
import { CustomText } from "../components/CustomText";
import AsennettuAnturiStore from "../stores/AsennettuAnturiStore";
import AnturikohtaisetTunnusarvotForm from "./AnturikohtaisetTunnusarvotForm";
import AsennuspaikanTyyppiForm from "./AsennuspaikanTyyppiForm";



const AsennettuAnturiForm = ({ asennettuAnturi, namespace, index }:
  { asennettuAnturi: AsennettuAnturiStore, namespace: string, index: number }
  ) => {
  return (
    <>
      <h3>{`Anturien ja anturikohtaisten arvojen lisääminen - Anturi ${index + 1}`}</h3>
      <div style={{ 
        padding: '10px',
        margin: '10px 0px 10px',
        border: '1px solid #DDDDDD',
        borderRadius: '4px',
        boxShadow: '0px 1px 1px rgb(0 0 0 / 5%)',
      }}>
        <CustomNumber
          label="Anturin sijainti Itä-koordinaatti metreinä (ETRS-TM35FIN)"
          name={`${namespace}.gps_lat`}
          readOnly={false}
        />
        <CustomNumber
          label="Anturin sijainti Pohjois-koordinaatti metreinä (ETRS-TM35FIN)"
          name={`${namespace}.gps_long`}
          readOnly={false}
        />
        <CustomText
          label="Sijoituspaikan lisäselite"
          name={`${namespace}.sijoituspaikan_lisaselite`}
          readOnly={false}
        />
        <CustomNumber
          label="Anturin asennuskerros rakennuksessa"
          name={`${namespace}.kerros`}
          readOnly={false}
        />
        <p>Tip: Asennuskerros: Maa tai sokkeli = 0, 1 kerros = 1 jne. kellari = -1</p>
        <AsennuspaikanTyyppiForm namespace={`${namespace}.asennuspaikanTyyppi`} paikka={asennettuAnturi.asennuspaikanTyyppi} />
        <CustomNumber
          label="Anturin etäisyys radasta metreinä"
          name={`${namespace}.etaisyys_radasta_jos_eri`}
          readOnly={false}
        />
      <AnturikohtaisetTunnusarvotForm namespace={`${namespace}.anturikohtaisetTunnusarvot`} tunnusarvot={asennettuAnturi.anturikohtaisetTunnusarvot} />
      </div>
    </>
  )
}

export default AsennettuAnturiForm;
