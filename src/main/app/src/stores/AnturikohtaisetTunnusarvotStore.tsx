import { observable } from "mobx";
import MittausSuuntaTypeEnum from "../types/enums/mittausSuuntaType.enum";
import IAnturikohtaisetTunnusarvot from "../types/interfaces/anturikohtaisetTunnusarvot.interface";

export default class AnturikohtaisetTunnusarvotStore implements IAnturikohtaisetTunnusarvot {
  @observable mittaussuunta_xyz = MittausSuuntaTypeEnum.X;
  @observable tarinan_maksimiarvo = 0;
  @observable hallitseva_taajuus = 0;
  @observable tarinan_tunnusluku_vw95_rms = 0;

}