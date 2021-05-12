import { IAnturikohtaisetTunnusarvo } from "./anturikohtaisetTunnusarvot.interface";
import IAsennuspaikantyyppi from "./asennuspaikanTyyppi.interface";


export default interface IAsennettuAnturi {
  malli: string
  gps_lat: string
  gps_long: string
  etaisyys_radasta_jos_eri: number
  kerros: number
  sijoituspaikan_lisaselite: string
  asennuspaikantyyppi: IAsennuspaikantyyppi
  anturikohtaisettunnusarvot: IAnturikohtaisetTunnusarvo[]
}
