import React from "react";
import Table from 'react-bootstrap/Table'
import { Button } from 'react-bootstrap';
import { Link } from 'react-router-dom';

import IMittaus from "../types/interfaces/mittaus.interface";


const getData = async (url = 'http://localhost:8080/mittaus/', data = {}, offset = 0) => {
    // Default options are marked with *
    const response = await fetch(url, {
      method: 'GET', // *GET, POST, PUT, DELETE, etc.
      mode: 'cors', // no-cors, *cors, same-origin
      cache: 'no-cache', // *default, no-cache, reload, force-cache, only-if-cached
      credentials: 'same-origin', // include, *same-origin, omit
      headers: {
        'Content-Type': 'application/json',
        // 'Content-Type': 'application/x-www-form-urlencoded',
      },
      redirect: 'follow', // manual, *follow, error
      referrerPolicy: 'no-referrer', // no-referrer, *no-referrer-when-downgrade, origin, origin-when-cross-origin, same-origin, strict-origin, strict-origin-when-cross-origin, unsafe-url
    });
    return response.json(); // parses JSON response into native JavaScript objects
  }

const MittausListView = () => {
    const [mittausData, setMittausData] = React.useState([]);

    const fetchAndSetData = async () => {
        const data = await getData();
        console.log(data);
        setMittausData(data);
    }

    React.useEffect(() => {
        fetchAndSetData();
    }, []);
    return (
      <>
        <div style={{'display': 'flex', 'float': 'right', 'padding': '5px 20px'}}>
          <Link to="/mittaus">
            <Button>Uusi</Button>
          </Link>
        </div>
        <Table striped>
          <thead>
            <th>
              Alkuaika - loppuaika
            </th>
            <th>
              Katuosoite
            </th>
            <th>
              Postinumero
            </th>
          </thead>
          <tbody>
            {mittausData ? mittausData.map((mittaus: IMittaus, idx) => (
              <tr key={idx}>
                <td>
                  {`${mittaus.alkuaika} - ${mittaus.loppuaika}`}
                </td>
                <td>
                  {mittaus.katuosoite || "ei osoitetta"}
                </td>
                <td>
                  {mittaus.postinumero || "ei postinumeroa"}
                </td>
              </tr>
            )) : "ei mittauksia"}
          </tbody>
        </Table>
      </>
    );
}

export default MittausListView;