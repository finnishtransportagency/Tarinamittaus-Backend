import React from "react";
import Table from 'react-bootstrap/Table'
import { Button } from 'react-bootstrap';
import { Link } from 'react-router-dom';
import Pagination from 'react-bootstrap/Pagination';

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

const itemsPerPage = 10;

const MittausListView = () => {
    const [mittausData, setMittausData] = React.useState([]);
    const [activePage, setActivePage] = React.useState(0);
    const [nItems, setNItems] = React.useState(0);

    const fetchAndSetData = async () => {
        const data = await getData();
        setMittausData(data);
        setNItems(data.length);
    }

    React.useEffect(() => {
        fetchAndSetData();
    }, []);

    const startIndex = activePage * itemsPerPage;
    const endIndex = startIndex + itemsPerPage;
    const nPages = Math.ceil(nItems/itemsPerPage);
    const pageIndexes = Array(nPages).fill(null).map((_, i) => i);
    const displayedPageIndexes = pageIndexes.slice(Math.max(0, activePage - 5), Math.min(nPages, activePage + 6));
    return (
      <>
        <div style={{'display': 'flex', 'float': 'right', 'padding': '5px 20px'}}>
          <Link to="/mittaus">
            <Button>Uusi</Button>
          </Link>
        </div>
        <Table striped>
          <thead>
            <tr>
              <th>
                Alkuaika - loppuaika
              </th>
              <th>
                Katuosoite
              </th>
              <th>
                Postinumero
              </th>
            </tr>
          </thead>
          <tbody>
            {mittausData ? mittausData.slice(startIndex, endIndex).map((mittaus: IMittaus, idx) => (
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
        <div style={{'display': 'flex', 'justifyContent': 'center'}}>
          <Pagination>
            <Pagination.First onClick={() => setActivePage(0)}/>
            {displayedPageIndexes.map((i) => (
              <Pagination.Item
                key={i}
                active={activePage === (i)}
                onClick={() => setActivePage(i)}>
                  {i + 1}
              </Pagination.Item>
            ))}
            <Pagination.Last onClick={() => setActivePage(nPages - 1)}/>
          </Pagination>
        </div>
      </>
    );
}

export default MittausListView;