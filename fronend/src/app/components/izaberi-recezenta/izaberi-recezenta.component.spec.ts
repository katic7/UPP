import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { IzaberiRecezentaComponent } from './izaberi-recezenta.component';

describe('IzaberiRecezentaComponent', () => {
  let component: IzaberiRecezentaComponent;
  let fixture: ComponentFixture<IzaberiRecezentaComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ IzaberiRecezentaComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(IzaberiRecezentaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
