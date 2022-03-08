export interface User {
  uid?: number,
  username: string,
  email: string,
  password: string
}

export interface Game {
  gameId?: number,
  title: string,
  developer: string,
  price: number,
  qtySold: number
}

