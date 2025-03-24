import {Directive, EventEmitter, HostListener, Output} from "@angular/core";

@Directive()
export abstract class ClosableModalBase {
  @Output() close = new EventEmitter<void>();

  @HostListener('document:keydown.escape', ['$event'])
  onEscape(event: KeyboardEvent) {
    this.close.emit();
  }
}
