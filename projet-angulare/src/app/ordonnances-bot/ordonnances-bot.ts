import {ChangeDetectorRef, Component, OnInit} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {FormsModule} from '@angular/forms';
import {Ordbot} from '../services/ordbot';

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
  constructor(private ordS:Ordbot, private cdr:ChangeDetectorRef) {}
  ngOnInit() {}

  sendMessage(){
    this.ordS.chatbotMessage(this.humanMessage).subscribe({
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
