export const ModuleInfo = {
  Home: { name: 'Home', path: '/home', role: undefined,params: 'menu'  },  // undefined means does not need permission
  Keycloak: { name: 'Keycloak', path: '/keycloak', role: 'keycloak' },
  Redis: { name: 'Redis', path: '/redis', role: 'redis'  },

} as const;

export type ModuleName = keyof typeof ModuleInfo;