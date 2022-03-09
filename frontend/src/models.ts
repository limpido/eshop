export interface User {
  uid?: number,
  username: string,
  email: string,
  password: string,
  token: string;
}

export interface Game {
  gameId?: number,
  title: string,
  developer: string,
  price: number,
  qtySold: number
}

