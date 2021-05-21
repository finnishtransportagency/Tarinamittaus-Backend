import React from 'react';

import { CustomNumber } from "../components/CustomNumber";
import { CustomText } from "../components/CustomText";
import AsennettuAnturiStore from "../stores/AsennettuAnturiStore";
import AnturikohtaisetTunnusarvotForm from "./AnturikohtaisetTunnusarvotForm";
import AsennuspaikanTyyppiForm from "./AsennuspaikanTyyppiForm";



const AsennettuAnturiForm = ({ asennettuAnturi, namespace }: { asennettuAnturi: AsennettuAnturiStore, namespace: string }) => {
  return (
    <>
      <h3>Anturien ja anturikohtaisten arvojen lisääminen</h3>
      <CustomNumber
        label="gps_lat"
        name={`${namespace}.gps_lat`}
        readOnly={false}
      />
      <CustomNumber
        label="gps_long"
        name={`${namespace}.gps_long`}
        readOnly={false}
      />
      <CustomText
        label="Sijoituspaikan lisäselite"
        name={`${namespace}.sijoituspaikan_lisaselite`}
        readOnly={false}
      />
      <CustomNumber
        label="Kerros"
        name={`${namespace}.kerros`}
        readOnly={false}
      />
      <p>Tip: Asennuskerros: Maa tai sokkeli = 0, 1 kerros = 1 jne. kellari = -1</p>
      <AsennuspaikanTyyppiForm namespace={`${namespace}.asennuspaikanTyyppi`} paikka={asennettuAnturi.asennuspaikanTyyppi} />
      <CustomNumber
        label="Etaisyys radasta jos eri"
        name={`${namespace}.etaisyys_radasta_jos_eri`}
        readOnly={false}
      />
      <CustomText
        label="Malli"
        name={`${namespace}.malli`}
        readOnly={false}
      />
      <AnturikohtaisetTunnusarvotForm namespace={`${namespace}.anturikohtaisetTunnusarvot`} tunnusarvot={asennettuAnturi.anturikohtaisetTunnusarvot} />

    </>
  )
}

export default AsennettuAnturiForm;