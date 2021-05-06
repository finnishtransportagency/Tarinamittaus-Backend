import { observable } from "mobx";
import SeliteTypeEnum from "../types/enums/seliteType.enum";
import IAsennuspaikantyyppi from "../types/interfaces/asennuspaikanTyyppi.interface";



export default class AsennuspaikanTyyppiStore implements IAsennuspaikantyyppi {
  @observable selite = SeliteTypeEnum.muu;
  @observable lisatiedot = '';

}