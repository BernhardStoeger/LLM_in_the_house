import {
  ApplicationConfig, inject,
  provideAppInitializer,
  provideBrowserGlobalErrorListeners,
  provideZonelessChangeDetection
} from '@angular/core';
import { provideRouter } from '@angular/router';

import { routes } from './app.routes';
import {provideHttpClient} from '@angular/common/http';
import {provideApi} from './api/backend-api';
import {userInitServiceFactory} from './app.module';
import {UserService} from './user/user-service';

export const appConfig: ApplicationConfig = {
  providers: [
    provideBrowserGlobalErrorListeners(),
    provideZonelessChangeDetection(),
    provideRouter(routes),
    provideHttpClient(),
    provideApi(''),
    provideAppInitializer(() => {
      const initializerFn = (userInitServiceFactory)(inject(UserService));
      return initializerFn();
    }),

  ]
};
