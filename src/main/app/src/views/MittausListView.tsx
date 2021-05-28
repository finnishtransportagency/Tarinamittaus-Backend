import React from "react";
// import PalveluGriddle from "./MittausGriddle";
import { inject } from "mobx-react";

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
// @inject("mittausStore")
// export default class MittausListView extends Component {

//     render() {
//         return getData();
//         // return <MittausGriddle dataStore={this.props.mittausStore} />;
//     }
// }


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
        <ul>
            {mittausData && mittausData.map(mittaus => (<li>{JSON.stringify(mittaus)}</li>))}
        </ul>
    );
}

export default MittausListView;