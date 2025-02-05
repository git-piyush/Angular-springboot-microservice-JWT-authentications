import { CanActivateFn } from '@angular/router';
import { inject } from '@angular/core';
import {  Router } from '@angular/router';
import { MatSnackBar } from '@angular/material/snack-bar';
import { StorageService } from '../../services/storage/storage.service';

//const USER = "c_user";
//const TOKEN = "c_token";

export const adminGuard: CanActivateFn = (route, state) => {
    const router = inject(Router);
    const snackbar = inject(MatSnackBar);
    var token = StorageService.getToken();
    if(token){
          var user = StorageService.getUser();
          if(user.active==='N'){
            snackbar.open("Please Activate your account.","Close", { duration: 5000 });
            router.navigateByUrl("/login");
          }
          return true;
    }else{
        snackbar.open("You don't have access to this page","Close", { duration: 5000 });
        router.navigateByUrl("/login");
        return false;
    }
  return true;
};
