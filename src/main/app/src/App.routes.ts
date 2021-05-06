import { RouteProps } from 'react-router-dom';
import MittausView from './views/MittausView';


const routes: RouteProps[] = [
  {
    path: '/mittaus',
    exact: true,
    component: MittausView,
  },
];

export default routes;
