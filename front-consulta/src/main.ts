import { bootstrapApplication } from '@angular/platform-browser';
import { appConfig } from './app/app.config';
import { ConsultaCreditosComponent } from './app/components/consulta-creditos/consulta-creditos.component';

bootstrapApplication(ConsultaCreditosComponent, appConfig)
  .catch((err) => console.error(err));
