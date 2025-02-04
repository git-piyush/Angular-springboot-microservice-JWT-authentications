import { inject } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { CanActivateFn, Router } from '@angular/router';
import { StorageService } from '../auth/services/storage/storage.service';

export const appGuard: CanActivateFn = (route, state) => {
  const router = inject(Router);
    const snackbar = inject(MatSnackBar);
    var token = StorageService.getToken();
    if(token){
          return true;
    }else{
        snackbar.open("You don't have access to this page","Close", { duration: 5000 });
        router.navigateByUrl("/login");
        return false;
    }
  return true;
};
