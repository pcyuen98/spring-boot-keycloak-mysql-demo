import { NgModule } from '@angular/core';
import { PreloadAllModules, RouterModule, Routes } from '@angular/router';
import { AuthGuard } from './service/AuthGuard';
import { Role } from './models/role';

const routes: Routes = [
  {
    path: '',
    redirectTo: 'home/undefined',
    pathMatch: 'full',
  },
  {
    path: 'keycloak',
    loadChildren: () => import('./keycloak/keycloak.module').then(m => m.KeycloakPageModule),
    canActivate: [AuthGuard],
    data: { roles: [Role.Keycloak, Role.Redis] }
  },
  {
    path: 'redis',
    loadChildren: () => import('./redis/redis.module').then(m => m.RedisPageModule),
    canActivate: [AuthGuard],
    data: { roles: [Role.Redis] }
  },
  {
    path: 'home/:redirect',
    loadChildren: () => import('./home/home.module').then(m => m.HomePageModule),
  },
  { path: '**', redirectTo: '' }
];

@NgModule({
  imports: [
    RouterModule.forRoot(routes, { preloadingStrategy: PreloadAllModules })
  ],
  exports: [RouterModule]
})
export class AppRoutingModule {

}
