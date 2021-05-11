import { observable } from "mobx";
import anturikohtaisetTunnusarvotInterface from "../types/interfaces/anturikohtaisetTunnusarvot.interface";
import IAsennettuAnturi from "../types/interfaces/asennettuAnturi.interface";
import asennuspaikanTyyppiInterface from "../types/interfaces/asennuspaikanTyyppi.interface";
import AsennuspaikanTyyppiStore from "./AsennuspaikanTyyppiStore";


export default class AsennettuAnturiStore implements IAsennettuAnturi {
  @observable malli = '';
  @observable gps_lat = '';
  @observable gps_long = '';
  @observable etaisyys_radasta_jos_eri = 0;
  @observable kerros = 0;
  @observable sijoituspaikan_lisaselite = '';
  @observable asennuspaikantyyppi = new AsennuspaikanTyyppiStore();
  @observable anturikohtaisettunnusarvot = [];
}