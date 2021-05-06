import { RouteProps } from 'react-router-dom';
import Basic from './components/Basic';


const routes: RouteProps[] = [
  {
    path: '/mittaus',
    exact: true,
    component: Basic,
  },
];

export default routes;
