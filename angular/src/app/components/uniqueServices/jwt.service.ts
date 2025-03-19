import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class JwtService {

  constructor() { }

  decodeToken(token: string): any {
    try {
      const tokenParts = token.split('.');
      if (tokenParts.length !== 3) {
        throw new Error('Invalid Token');
      }

      // 🔹 Dekodowanie części payload (środkowej części JWT)
      const decodedPayload = JSON.parse(atob(tokenParts[1]));
      return decodedPayload;
    } catch (error) {
      console.error('Błąd dekodowania tokena:', error);
      return null;
    }
  }
}
