import { observable } from 'mobx';

import asennettuAnturiInterface from "../types/interfaces/asennettuAnturi.interface";
import IMittaus from "../types/interfaces/mittaus.interface";
import AsennettuAnturiStore from './AsennettuAnturiStore';


export default class MittausStore implements IMittaus {

  @observable alkuaika = '';
  @observable loppuaika = '';
  @observable mittaus_asianhallinta_id = '';
  @observable pdf_raportin_linkki = '';
  @observable rakennuksen_pinta_ala = 0;
  @observable perustamistapa = '';
  @observable julkisivumateriaali = '';
  @observable runkomateriaali = '';
  @observable rakennusvuosi = 0;
  @observable katuosoite = '';
  @observable postinumero = '';
  @observable created_by_lx = '';
  @observable asennettuAnturi = [];

}