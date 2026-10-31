import {ChangeDetectorRef, Component, OnInit} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {FormsModule} from '@angular/forms';

@Component({
  selector: 'app-ordonnances-bot',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './ordonnances-bot.html',
  styleUrl: './ordonnances-bot.css',
})
export class OrdonnancesBot implements OnInit{
  humanMessage:any;
  AiReposne:any;
  constructor(private Http:HttpClient, private cdr:ChangeDetectorRef) {}
  ngOnInit() {}

  sendMessage(){
    this.Http.get("http://localhost:8886/ORDONNANCE-BOT/chat",
      { params: { query: this.humanMessage }, responseType: 'text' }).subscribe({
      next:data=>{
        this.AiReposne=data;
        this.cdr.detectChanges();
      },
      error:err=>{
        console.log(err)
      }
    })
  }
}
