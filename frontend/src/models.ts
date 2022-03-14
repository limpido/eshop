export interface User {
  uid?: number,
  username: string,
  email: string,
  password: string,
  token: string;
}

export interface Game {
  gameId: number,
  title: string,
  image_path: string,
  genre: string,
  developer: string,
  price: number,
  qtySold: number,
  qtyOrdered?: number,
  total?: number
}

