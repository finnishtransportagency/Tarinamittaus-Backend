import { RouteProps } from 'react-router-dom';
import MittausListView from './views/MittausListView';
import MittausView from './views/MittausView';


const routes: RouteProps[] = [
  {
    path: '/mittaus',
    exact: true,
    component: MittausView,
  },
  {
    path: '/mittauslista',
    exact: true,
    component: MittausListView,
  },
];

export default routes;
