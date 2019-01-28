export class ApiResponse {

  status: number;
  message: string;
  result: any;

  constructor (status: number, message: string, result: any) {
    this.status = status;
    this.message = message;
    this.result = result;
 }
}
