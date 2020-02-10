import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { OdlukaUrednikaComponent } from './odluka-urednika.component';

describe('OdlukaUrednikaComponent', () => {
  let component: OdlukaUrednikaComponent;
  let fixture: ComponentFixture<OdlukaUrednikaComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ OdlukaUrednikaComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(OdlukaUrednikaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
