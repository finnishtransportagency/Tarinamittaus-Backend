import { observable } from "mobx";
import MittausSuuntaTypeEnum from "../types/enums/mittausSuuntaType.enum";
import { IAnturikohtaisetTunnusarvo } from "../types/interfaces/anturikohtaisetTunnusarvot.interface";
import IAsennettuAnturi from "../types/interfaces/asennettuAnturi.interface";
import AsennuspaikanTyyppiStore from "./AsennuspaikanTyyppiStore";


export default class AsennettuAnturiStore implements IAsennettuAnturi {
  @observable malli = '';
  @observable gps_lat = 0;
  @observable gps_long = 0;
  @observable etaisyys_radasta_jos_eri = 0;
  @observable kerros = 0;
  @observable sijoituspaikan_lisaselite = '';
  @observable asennuspaikanTyyppi = new AsennuspaikanTyyppiStore();
  @observable anturikohtaisetTunnusarvot = tunnusArvot;
}

let tunnusArvot: IAnturikohtaisetTunnusarvo[] = [
  {
    mittaussuunta_xyz: MittausSuuntaTypeEnum.X,
    tarinan_maksimiarvo: 0,
    hallitseva_taajuus: 0,
    tarinan_tunnusluku_vw95_rms: 0,
  },
  {
    mittaussuunta_xyz: MittausSuuntaTypeEnum.Y,
    tarinan_maksimiarvo: 0,
    hallitseva_taajuus: 0,
    tarinan_tunnusluku_vw95_rms: 0,
  },
  {
    mittaussuunta_xyz: MittausSuuntaTypeEnum.Z,
    tarinan_maksimiarvo: 0,
    hallitseva_taajuus: 0,
    tarinan_tunnusluku_vw95_rms: 0,
  }
];